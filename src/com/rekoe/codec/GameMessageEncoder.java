package com.rekoe.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.rekoe.msg.BaseIoMessage;
/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
public class GameMessageEncoder extends OneToOneEncoder {

	@Override
	protected Object encode(ChannelHandlerContext context, Channel channel,
			Object message) throws Exception {
		if (!(message instanceof BaseIoMessage)) {
			return message;
		}
		BaseIoMessage<?> _msg = (BaseIoMessage<?>) message;
		ChannelBuffer _buffer = ChannelBuffers.dynamicBuffer();
		_msg.setBuffer(_buffer);
		_msg.write();
		return _msg.getBuffer();
	}

}