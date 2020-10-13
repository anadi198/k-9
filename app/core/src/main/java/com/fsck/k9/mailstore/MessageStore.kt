package com.fsck.k9.mailstore

/**
 * Functions for accessing and modifying locally stored messages.
 *
 * The goal is for this to gradually replace [LocalStore]. Once complete, apps will be able to provide their own
 * storage implementation.
 */
interface MessageStore {
    /**
     * Move a message to another folder.
     *
     * @return The message's database ID in the destination folder. This will most likely be different from the
     *   messageId passed to this function.
     */
    fun moveMessage(messageId: Long, destinationFolderId: Long): Long

    /**
     * Move messages to another folder.
     *
     * @return A mapping of the original message database ID to the new message database ID.
     */
    fun moveMessages(messageIds: List<Long>, destinationFolderId: Long): Map<Long, Long> {
        return messageIds
            .map { messageId ->
                messageId to moveMessage(messageId, destinationFolderId)
            }
            .toMap()
    }
}
