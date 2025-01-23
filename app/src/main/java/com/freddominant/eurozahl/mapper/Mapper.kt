package com.freddominant.eurozahl.mapper

interface Mapper<I,O> {
    fun map(arg: I) : O
}