package com.superhonor.rpc.common;

import com.superhonor.rpc.util.SerializationUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import java.util.List;

/**
 * RPC解码器
 *
 * @author liuweidong
 */
public class RpcDecoder extends ProtobufVarint32FrameDecoder {

    @Override
    public final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (dataLength < 0) {
            ctx.close();
        }
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }
        // 将ByteBuf转换为byte[]
        byte[] data = new byte[dataLength];
        in.readBytes(data);
        // 将data转换成object
        Object obj = SerializationUtils.deserialize(data);
        out.add(obj);
    }
}
