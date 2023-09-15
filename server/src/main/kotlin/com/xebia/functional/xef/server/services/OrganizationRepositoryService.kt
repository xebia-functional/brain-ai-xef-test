package com.xebia.functional.xef.server.services

import com.xebia.functional.xef.server.db.tables.Organization
import com.xebia.functional.xef.server.db.tables.User
import com.xebia.functional.xef.server.db.tables.UsersTable
import com.xebia.functional.xef.server.models.*
import kotlinx.datetime.Clock
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.Logger

class OrganizationRepositoryService(
    private val logger: Logger
) {
    fun createOrganization(
        data: OrganizationRequest,
        token: String
    ): OrganizationSimpleResponse {
        logger.info("Creating organization with name: ${data.name}")
        return transaction {
            // Getting the user from the token
            val user = getUser(token)

            // Creating the organization
            val organization = Organization.new {
                name = data.name
                ownerId = user.id
            }
            // Adding the organization to the user
            user.organizations = SizedCollection(user.organizations + organization)
            organization.users = SizedCollection(organization.users + user)
            OrganizationSimpleResponse(organization.name)
        }
    }

    fun getOrganizations(
        token: String
    ): List<OrganizationWithIdResponse> {
        logger.info("Getting organizations")
        return transaction {
            // Getting the user from the token
            val user = getUser(token)

            // Getting the organizations from the user
            user.organizations.map { OrganizationWithIdResponse(it.id.value, it.name, it.users.count()) }
        }
    }

    fun getOrganization(
        token: String,
        id: Int
    ): List<OrganizationFullResponse> {
        logger.info("Getting organizations")
        return transaction {
            // Getting the user from the token
            val user = getUser(token)

            // Getting the organizations from the user
            user.organizations.filter {
                it.id.value == id
            }.map { OrganizationFullResponse(it.id.value, it.name, it.ownerId.value, it.users.count()) }
        }
    }

    fun getUsersInOrganization(
        token: String,
        id: Int
    ): List<UserResponse> {
        logger.info("Getting users in organization")
        return transaction {
            // Getting the user from the token
            val user = getUser(token)

            // Getting the organizations from the user
            user.organizations.filter {
                it.id.value == id
            }.flatMap { it.users }.map { UserResponse(it.id.value, it.name) }
        }
    }

    fun updateOrganization(
        token: String,
        data: OrganizationUpdateRequest,
        id: Int
    ): OrganizationFullResponse {
        logger.info("Updating organization with name: ${data.name}")
        return transaction {
            // Getting the user from the token
            val user = getUser(token)

            val organization = Organization.findById(id)
                ?: throw Exception("Organization not found")

            if (organization.ownerId != user.id) {
                throw Exception("User is not the owner of the organization")
            }

            // Updating the organization
            organization.name = data.name
            if (data.owner != null) {
                val newOwner = User.findById(data.owner)
                    ?: throw Exception("User not found")
                organization.ownerId = newOwner.id
            }
            organization.updatedAt = Clock.System.now()
            OrganizationFullResponse(
                organization.id.value,
                organization.name,
                organization.ownerId.value,
                organization.users.count()
            )
        }
    }

    fun deleteOrganization(
        token: String,
        id: Int
    ) {
        logger.info("Deleting organization with id: $id")
        transaction {
            val user = getUser(token)
            val organization = Organization.findById(id)
                ?: throw Exception("Organization not found")
            if (organization.ownerId == user.id) {
                organization.delete()
            }
        }
    }

    private fun getUser(token: String) =
        User.find { UsersTable.authToken eq token }.firstOrNull() ?: throw Exception("User not found")
}
