package com.taushsampley.timer.tasks.data

import androidx.room.*

/**
 * Room entity (DTO) for [com.taushsampley.timer.tasks.Category].
 */
@Entity(
    tableName = CategoryDto.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = CategoryDto::class,
        parentColumns = [CategoryDto.ID_NAME],
        childColumns = [CategoryDto.PARENT_NAME]
    )],
    indices = [Index(CategoryDto.PARENT_NAME, CategoryDto.TITLE_NAME, unique = true)]
)
data class CategoryDto(
    /**
     * A simple human-readable title of this category.
     */
    @ColumnInfo(name = TITLE_NAME)
    val title: String,


    /**
     * An optional category this category belongs to
     */
    @ColumnInfo(name = PARENT_NAME)
    val category: Long? = null,

    /**
     * Unique row id for this category entry.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_NAME)
    val id: Long = 0
) {
    companion object {
        const val TABLE_NAME = "categories"
        const val ID_NAME = "cat_id"
        const val TITLE_NAME = "cat_title"
        const val PARENT_NAME = "parent"
    }
}
