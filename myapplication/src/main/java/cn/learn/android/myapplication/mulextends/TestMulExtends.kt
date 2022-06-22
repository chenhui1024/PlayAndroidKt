package cn.learn.android.myapplication.mulextends

/**
 * @description
 * @author ch
 * @date 2021/9/3
 * @edit
 */

open class ClsA

interface IntA

interface IntB

class Impl : ClsA(), IntA, IntB

abstract class BaseCls<T> where T : ClsA, T : IntA, T : IntB

class BaseClsImpl : BaseCls<Impl>()



