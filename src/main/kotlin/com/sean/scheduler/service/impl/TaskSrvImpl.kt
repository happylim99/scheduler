package com.sean.scheduler.service.impl

import com.sean.base.exception.CException
import com.sean.base.ext.getUUID
import com.sean.scheduler.entity.Task
import com.sean.scheduler.repo.TaskRepo
import com.sean.scheduler.service.BaseSrv
import com.sean.scheduler.service.TaskSrv
import com.sean.scheduler.ui.req.TaskCrtReq
import com.sean.scheduler.ui.req.TaskUptReq
import com.sean.scheduler.util.AppUtil
import org.springframework.beans.BeanUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TaskSrvImpl(
    private val repo: TaskRepo,
    private val scheduler: Scheduler
) : BaseSrv<Task, TaskRepo>(

), TaskSrv {

    override fun getRepo() = repo

    override fun toggleSchedule(uid: String): String {
        var task = repo.findByUid(uid)
        if(task == null) {
            throw CException("Record not found");
        } else {
            task.schedule = !task.schedule
            val task = repo.save(task)
            scheduler.toggleSchedule(task)
            return "OK"
        }
        return "NOT OK"
    }

    override fun createOne(req: TaskCrtReq): Task {
    	var obj = Task()
    	BeanUtils.copyProperties(req, obj)
    	obj.uid = getUUID()
    	return repo.save(obj)
    }

    override fun updateOne(uid: String, req: TaskUptReq): Task {
    	var dbObj = repo.findByUid(uid) ?: throw CException("Object not found")
    	var obj = Task()
    	BeanUtils.copyProperties(dbObj, obj, "id")
    	BeanUtils.copyProperties(req, obj as Any, "id")
    	repo.deleteByUid(uid)
    	return repo.save(obj)
    }

    override fun showOne(uid: String) = repo.findByUid(uid)

    override fun deleteOne(uid: String): String {
    	val theId = repo.deleteByUid(uid)
    	return if(theId == null) "not ok" else "ok"
    }

    override fun showAllValid() = customFindAll(false)

    override fun showAllVoid() = customFindAll(true)

    override fun showAll() = findAll()

}