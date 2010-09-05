
//package com.gn.gae;
package com.gn.gae.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

//import net.kindleit.gae.example.model.Message;
import com.gn.gae.model.Message;

public class MessageRepository {

	PersistenceManagerFactory pmfInstance = JDOHelper.getPersistenceManagerFactory("transactions-optional");

	public Collection<Message> getAll() {
		PersistenceManager pm = pmfInstance.getPersistenceManager();
		try {
			List<Message> messages = new ArrayList<Message>();
		    Extent<Message> extent = pm.getExtent(Message.class, false);
		    for (Message message : extent) {
		        messages.add(message);
		    }
		    extent.closeAll();
			
		    return messages;
		} finally {
			pm.close();
		}
	}

	public void create(Message message) {
		PersistenceManager pm = pmfInstance.getPersistenceManager();
		try {
		    pm.makePersistent(message);
		} finally {
			pm.close();
		}
	}

	public void deleteById(Long id) {
		PersistenceManager pm = pmfInstance.getPersistenceManager();
		try {
			pm.deletePersistent(pm.getObjectById(Message.class, id));
		} finally {
			pm.close();
		}
	}
}
