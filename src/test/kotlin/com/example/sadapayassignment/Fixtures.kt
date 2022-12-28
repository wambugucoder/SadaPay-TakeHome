package com.example.sadapayassignment

import java.io.File
import java.net.URLDecoder

object Fixtures {

    const val POSTGRES_IMAGE = "postgres:11.5"
    const val POSTGRES_DBNAME = "testDb"
    const val POSTGRES_USERNAME = "test"
    const val POSTGRES_PASSWORD = "test"
    const val DISABLE_WEB_ENV_ARG = "--spring.main.web-environment=false"
    const val REPO_PACKAGE = "com.example.sadapayassignment.repository"
    const val MODEL_PACKAGE = "com.example.sadapayassignment.model"

    fun getClassesInPackage(packageName: String): List<Class<*>> {
        val classLoader = Thread.currentThread().contextClassLoader
        val packagePath = packageName.replace(".", "/")
        val resources = classLoader.getResources(packagePath)

        val classes = mutableListOf<Class<*>>()
        while (resources.hasMoreElements()) {
            val resource = resources.nextElement()
            if (resource.protocol == "file") {
                val filePath = URLDecoder.decode(resource.file, "UTF-8")
                val packageDirectory = File(filePath)
                if (packageDirectory.isDirectory) {
                    packageDirectory.listFiles()?.forEach { file ->
                        if (file.isFile) {
                            val className = "$packageName.${file.nameWithoutExtension}"
                            classes.add(Class.forName(className))
                        }
                    }
                }
            }
        }
        return classes
    }
}