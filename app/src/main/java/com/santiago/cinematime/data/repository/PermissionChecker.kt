package com.santiago.cinematime.data.repository

interface PermissionChecker {

    enum class Permission { COARSE_LOCATION }

    suspend fun check(permission: Permission): Boolean
}