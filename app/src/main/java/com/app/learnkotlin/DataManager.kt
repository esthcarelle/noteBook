package com.app.learnkotlin

import java.util.*

object DataManager{
     val courses = HashMap<String,CourseInfo>()
     val notes = ArrayList<NoteInfo>();

    init {
        initializeCourse()
        initializeNotes()
    }
    private fun initializeCourse(){
        var course = CourseInfo("android_intents","Android programming with Intents")
        courses.set(course.courseId,course)

        course = CourseInfo("android_async","Android programming with async")
        courses.set(course.courseId,course)

        course = CourseInfo("java_fundamentals","Android programming with fund")
        courses.set(course.courseId,course)
    }
    private fun initializeNotes(){
        var course = CourseInfo("android_intents","Android programming with Intents")

        var noteInfo = NoteInfo(course,"my note","hey hey hey")
        notes.add(noteInfo)

        noteInfo = NoteInfo(course,"my hhgh note","hey hey hey")
        notes.add(noteInfo)

    }
}