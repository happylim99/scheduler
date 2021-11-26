package com.sean.scheduler.task

import com.sean.scheduler.service.MasterScheduler
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.*

@Component("TaskTwo")
class TaskTwo: MasterScheduler(), Runnable {
    override fun run() {
        println("${Date()} runnable here")
    }

}