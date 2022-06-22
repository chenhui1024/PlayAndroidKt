package com.example.playandroid_kt.rengwuxian.review

/**
 * @description
 * @author ch
 * @date 2021/3/29
 * @edit
 */
class TopStudent constructor(name: String, age: Int, sex: String) : Student(name, age, sex) {

    constructor(name: String, age: Int) : this(name, age, "man")

}