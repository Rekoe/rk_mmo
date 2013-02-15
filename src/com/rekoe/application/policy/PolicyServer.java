package com.rekoe.application.policy;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.nutz.lang.Files;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.rekoe.mvc.AbstractServer;
import com.rekoe.mvc.config.GameConfig;

public class PolicyServer extends AbstractServer{
	
	private Log log = Logs.get();
    private static final String FLASH_POLICY_FILE = "sample_flash_policy.xml";
    private final StringBuffer FLASH_POLICY_STR = new StringBuffer();
    private final static int PORT = 843;
	@Override
	public void connect(GameConfig config) {
		ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));
        // Set up the event pipeline factory.
        bootstrap.setPipelineFactory(new FlashPolicyServerPipelineFactory(FLASH_POLICY_STR.toString()));
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        // Bind and start to accept incoming connections.
        bootstrap.bind(new InetSocketAddress(PORT));
		if (log.isInfoEnabled())
			log.infof("PolicyServerStart application Monitor at  Port %s" ,PORT);
	}

	@Override
	public void initMessageExecutor() {
		FLASH_POLICY_STR.append(Files.read(FLASH_POLICY_FILE));
	}
	
}
