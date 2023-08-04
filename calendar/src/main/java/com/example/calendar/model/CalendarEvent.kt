package com.example.calendar.model

import androidx.compose.ui.graphics.Color
import java.util.Calendar

class CalendarEvent(
    val color: Color,
    val startDate: Calendar,
    val endDate: Calendar
)