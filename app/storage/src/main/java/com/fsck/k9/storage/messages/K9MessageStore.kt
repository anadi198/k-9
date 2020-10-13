package com.fsck.k9.storage.messages

import com.fsck.k9.mailstore.LocalStore
import com.fsck.k9.mailstore.MessageStore

// TODO: Remove dependency on LocalStore
class K9MessageStore(localStore: LocalStore) : MessageStore {
    private val messageThreader = MessageThreader(localStore)
    private val messageMover = MessageMover(localStore.database, messageThreader)

    override fun moveMessage(messageId: Long, destinationFolderId: Long): Long {
        return messageMover.moveMessage(messageId, destinationFolderId)
    }
}
