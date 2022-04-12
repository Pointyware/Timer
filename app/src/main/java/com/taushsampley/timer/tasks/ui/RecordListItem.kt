package com.taushsampley.timer.tasks.ui

/**
 * Holds the information relevant to displaying a particular record.
 *
 * @property duration The duration of the record in seconds formatted by [RecordFormatter].
 */
data class RecordListItem(
    val title: String,
    val duration: String
)
