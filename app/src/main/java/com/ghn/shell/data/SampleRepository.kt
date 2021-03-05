package com.ghn.shell.data

class SampleRepository constructor(private val sampleDao: SampleDao) {

    fun getSamples() = sampleDao.getSamples()

    fun getSample(id: String) = sampleDao.getSample(id)

    suspend fun updateDataAll(samples: List<Sample>) = sampleDao.updateDataAll(samples)
}