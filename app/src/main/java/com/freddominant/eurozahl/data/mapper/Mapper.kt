package com.freddominant.eurozahl.data.mapper

interface Mapper<I,O> {
    fun map(arg: I) : O
}