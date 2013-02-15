package com.rekoe.msg;

import org.jboss.netty.buffer.ChannelBuffer;
import org.nutz.log.Log;
import org.nutz.log.Logs;


public abstract class BaseIoMessage <T extends IMessageSender>implements IMessage<T> {
	
	private final static Log log = Logs.get();
	private ChannelBuffer buffer;
	
	public abstract void writeImpl();
	
	public abstract void readImpl();

	public void write() {
		buffer.clear();
		buffer.markWriterIndex();
		buffer.writeInt(0);
		int _pos = buffer.writerIndex();
		buffer.writeShort(getType());
		writeImpl();
		int _len = buffer.writerIndex() - _pos;
		_pos = buffer.writerIndex();
		buffer.resetWriterIndex();
		buffer.writeInt(_len);
		buffer.setIndex(0, _pos);
	}
	public void read() {
		readImpl();
	}
	
	public void writeInt(int value) {
		buffer.writeInt(value);
	}
	
	public void writeLong(long value) {
		buffer.writeLong(value);
	}
	
	public void writeByte(byte value) {
		buffer.writeByte(value);
	}
	
	public void writeBytes(byte[] _bytes)
	{
		buffer.writeBytes(_bytes);
	}
	public void writeString(String value) {
		byte[] _bytes = value.getBytes();
		log.infof("write String len %s",_bytes.length);
		buffer.writeShort(_bytes.length);
		buffer.writeBytes(_bytes);
	}
	
	public void writeShort(short value) {
		buffer.writeShort(value);
	}
	
	public void writeFloat(float value) {
		buffer.writeFloat(value);
	}
	
	public void writeDouble(double value) {
		buffer.writeDouble(value);
	}
	
	public int readInt() {
		return buffer.readInt();
	}
	
	public long readLong() {
		return buffer.readLong();
	}
	
	public short readShort() {
		return buffer.readShort();
	}
	
	public byte readByte() {
		return buffer.readByte();
	}
	
	public float readFloat() {
		return buffer.readFloat();
	}
	
	public double readDouble() {
		return buffer.readDouble();
	}
	
	public String readString() {
		String value = null;
		int _len = readShort();//readInt();
		log.infof("read String len %s",_len);
		if(_len > 0) {
			byte[] _bytes = new byte[_len];
			buffer.readBytes(_bytes);
			value = new String(_bytes);
		}
		return value;
	}
	
	public ChannelBuffer getBuffer() {
		return this.buffer;
	}

	public void setBuffer(ChannelBuffer buffer) {
		this.buffer = buffer;
	}
	
}
