package com.codinginflow.mvvmtodo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.codinginflow.mvvmtodo.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)

abstract class TaskDatabase : RoomDatabase(){

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Wash the dishes"))
                dao.insert(Task("Learn OOP", important = true))
                dao.insert(Task("Learn Kotlin", completed = true))
                dao.insert(Task("Learn DataStructures"))
                dao.insert(Task("Learn Databases", completed = true))
                dao.insert(Task("Learn Lifecycle"))
                dao.insert(Task("Pay Prob"))
                dao.insert(Task("Learn ATP2"))
                dao.insert(Task("Learn CN"))
            }
        }
    }
}