package com.rekoe.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

import com.rekoe.model.gameobjects.client.Client;
import com.rekoe.msg.BaseIoMessage;
import com.rekoe.msg.IMessage;
import com.rekoe.msg.IMessageRecognizer;

/**
 * http://docs.jboss.org/netty/3.1/api/org/jboss/netty/handler/codec/frame/FrameDecoder.html
 * @author rekoe
 * Offset:  4           2         (Length + messageType + 4)
          +--------+-------------+------------------------+
 Fields:  | Length | messageType | Actual message content |
          +--------+-------------+------------------------+

 */
public class GameMessageDecoder extends FrameDecoder {

	
	private IMessageRecognizer<Integer> recognizer;

	public GameMessageDecoder(IMessageRecognizer<Integer> recognizer) {
		this.recognizer = recognizer;
	}

	@Override
	protected Object decode(ChannelHandlerContext context, Channel channel,
			ChannelBuffer buffer) throws Exception {
		/**
		 * if (buffer.readableBytes() < 6) {
			return null;
		}
		int expectLen = buffer.getInt( buffer.readerIndex() );
		int buffCurLen = buffer.readableBytes();
		if (buffCurLen < expectLen) {
			return null;
		}
		int msgLen = buffer.readInt();//@@@
		short msgType = buffer.readShort();//@@@
		IMessage msg = recognizer.createMsg(msgType);
		if (null == msg || !(msg instanceof BaseIOMsg)) {
			int msgContentLen = msgLen-6;
			if(0 < msgContentLen) {
				buffer.readBytes(msgContentLen);//@@@读指针跳过 未识别的消息
			}
			return null;
		}
		 */
		if (buffer.readableBytes() < 6) {
			return null;
		}
		buffer.markReaderIndex();
		int _len = buffer.readInt();
		int totalLen = buffer.readableBytes();
		if (totalLen < _len - 4) {
			buffer.resetReaderIndex();
			return null;
		}
		int _type = buffer.readShort();
		IMessage<Client> _msg = recognizer.createMessage(_type);
		if (_msg == null || !(_msg instanceof BaseIoMessage)) {
			if(0 < totalLen-2) {
				buffer.readBytes(totalLen-2);//读指针跳过 未识别的消息
			}
			return null;
		}
		BaseIoMessage<Client> _baseMsg = (BaseIoMessage<Client>) _msg;
		_baseMsg.setBuffer(buffer);
		_baseMsg.read();
		return _msg;
	}

}
