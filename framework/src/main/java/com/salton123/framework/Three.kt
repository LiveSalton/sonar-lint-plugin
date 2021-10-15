package com.salton123.framework

/**
 * Time:2021/10/14 17:06
 * Author:
 * Description:
 */
class Three {
    fun test(){
        var target = -5
        val num = 3

        target =- num // Noncompliant; target = -3. Is that really what's meant?
        target =+ num // Noncompliant; target = 3
    }
}