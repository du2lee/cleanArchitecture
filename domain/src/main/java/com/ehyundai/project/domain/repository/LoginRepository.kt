package com.ehyundai.project.domain.repository

interface LoginRepository {
    var autoLogin: Boolean
        get() = true
        set(value) {

        }
}