package com.jesse.pokedex.data.dao

import androidx.room.*
import be.appwise.room.BaseRoomDao
import com.jesse.pokedex.data.entities.favorite.FavoritePokemon
import com.jesse.pokedex.data.entities.pokemon.Pokemon
import com.jesse.pokedex.data.entities.team.TeamMember
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TeamDao : BaseRoomDao<TeamMember>(TeamMember.TABLE_NAME) {

    @Query("SELECT COUNT(*) FROM ${TeamMember.TABLE_NAME} WHERE id = :id")
    abstract suspend fun checkIfPokemonIsTeamMember(id: Int): Int

    @Query("SELECT COUNT(*) FROM ${TeamMember.TABLE_NAME}")
    abstract suspend fun countTeamMembers(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun setPokemonAsTeamMember(pokemon: TeamMember)

    @Delete
    abstract suspend fun removePokemonAsTeamMember(pokemon: TeamMember)

    @Transaction
    open suspend fun togglePokemonIsTeamMember(pokemon: Pokemon) {
        if(countTeamMembers() >= TeamMember.MAX_MEMBERS)
            throw MaxMemberException()
        if (checkIfPokemonIsTeamMember(pokemon.id) != 0) {
            setPokemonAsTeamMember(TeamMember(pokemon.id))
        } else {
            removePokemonAsTeamMember(TeamMember(pokemon.id))
        }
    }

    @Query("SELECT * FROM pokemon WHERE id IN ( SELECT id FROM ${TeamMember.TABLE_NAME})")
    abstract fun findTeamMemberPokemons(): Flow<List<Pokemon>>

    class MaxMemberException: IllegalStateException("Max member count of ${TeamMember.MAX_MEMBERS} reached.")
}