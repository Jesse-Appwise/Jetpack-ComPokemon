package com.jesse.pokedex.data.entities.team

import androidx.room.Entity
import androidx.room.PrimaryKey
import be.appwise.room.BaseEntity

@Entity(
    tableName = TeamMember.TABLE_NAME
)
data class TeamMember(
    @PrimaryKey override val id: Int
) : BaseEntity {

    companion object {
        const val TABLE_NAME = "teamMember"
        const val MAX_MEMBERS = 6
    }

}