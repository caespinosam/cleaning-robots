package com.vwdh.robot.domain.core.entity

abstract class AggregateRoot<ID>(override val id: ID) : BaseEntity<ID>(id)