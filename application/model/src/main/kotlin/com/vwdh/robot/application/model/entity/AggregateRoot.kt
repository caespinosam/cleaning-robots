package com.vwdh.robot.application.model.entity

abstract class AggregateRoot<ID>(override val id: ID) : BaseEntity<ID>(id)