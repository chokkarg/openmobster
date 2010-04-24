/**
 * 
 */
package org.openmobster.core.dataService.comet;

import org.apache.log4j.Logger;
import java.util.TimerTask;

/**
 * @author openmobster@gmail
 *
 */
class KeepAliveDaemon extends TimerTask
{
	private static Logger log = Logger.getLogger(KeepAliveDaemon.class);
	
	private CometSession session;
	private long pulseInterval;
	
	KeepAliveDaemon(long pulseInterval,CometSession session)
	{
		if(session == null)
		{
			throw new IllegalArgumentException("CometSession should not be null!!");
		}
		
		this.session = session;
		this.pulseInterval = pulseInterval;
	}
	
	@Override
	public void run()
	{
		if(this.session.isActive())
		{
			log.debug("---------------------------------------------------------------");
			log.debug("Sender: "+this.hashCode());
			log.debug("Sending a KeepAlive HeartBeat Every: ("+this.pulseInterval+" ms)");
			log.debug("---------------------------------------------------------------");
			this.session.sendHeartBeat();
		}
		else
		{
			log.debug("-----------------------------------------");
			log.debug("KeepAlive Daemon is done!!!");
			log.debug("-----------------------------------------");
			this.cancel(); //unschedules its execution
		}
	}	
}
