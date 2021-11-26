package com.sean.scheduler.task

import org.springframework.stereotype.Component
import java.util.*

@Component("TaskOne")
class TaskOne: Runnable {
    override fun run() {
        println("${Date()} runnable here")
    }

}