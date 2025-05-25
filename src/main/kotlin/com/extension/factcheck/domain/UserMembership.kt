package com.extension.factcheck.domain

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "user_memberships")
@Entity
class UserMembership(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val userId: Long,
    @Enumerated(EnumType.STRING)
    val grade: UserMembershipGrade = UserMembershipGrade.FREE,
) : BaseTimeEntity()

enum class UserMembershipGrade {
    FREE, PREMIUM
}
