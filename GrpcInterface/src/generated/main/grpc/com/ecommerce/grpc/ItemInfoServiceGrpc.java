package com.ecommerce.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: itemInfo.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ItemInfoServiceGrpc {

  private ItemInfoServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "ItemInfoService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ecommerce.grpc.ItemRequest,
      com.ecommerce.grpc.ItemReply> getGetItemInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getItemInfo",
      requestType = com.ecommerce.grpc.ItemRequest.class,
      responseType = com.ecommerce.grpc.ItemReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ecommerce.grpc.ItemRequest,
      com.ecommerce.grpc.ItemReply> getGetItemInfoMethod() {
    io.grpc.MethodDescriptor<com.ecommerce.grpc.ItemRequest, com.ecommerce.grpc.ItemReply> getGetItemInfoMethod;
    if ((getGetItemInfoMethod = ItemInfoServiceGrpc.getGetItemInfoMethod) == null) {
      synchronized (ItemInfoServiceGrpc.class) {
        if ((getGetItemInfoMethod = ItemInfoServiceGrpc.getGetItemInfoMethod) == null) {
          ItemInfoServiceGrpc.getGetItemInfoMethod = getGetItemInfoMethod =
              io.grpc.MethodDescriptor.<com.ecommerce.grpc.ItemRequest, com.ecommerce.grpc.ItemReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getItemInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ecommerce.grpc.ItemRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ecommerce.grpc.ItemReply.getDefaultInstance()))
              .setSchemaDescriptor(new ItemInfoServiceMethodDescriptorSupplier("getItemInfo"))
              .build();
        }
      }
    }
    return getGetItemInfoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ItemInfoServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ItemInfoServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ItemInfoServiceStub>() {
        @java.lang.Override
        public ItemInfoServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ItemInfoServiceStub(channel, callOptions);
        }
      };
    return ItemInfoServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ItemInfoServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ItemInfoServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ItemInfoServiceBlockingStub>() {
        @java.lang.Override
        public ItemInfoServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ItemInfoServiceBlockingStub(channel, callOptions);
        }
      };
    return ItemInfoServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ItemInfoServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ItemInfoServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ItemInfoServiceFutureStub>() {
        @java.lang.Override
        public ItemInfoServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ItemInfoServiceFutureStub(channel, callOptions);
        }
      };
    return ItemInfoServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getItemInfo(com.ecommerce.grpc.ItemRequest request,
        io.grpc.stub.StreamObserver<com.ecommerce.grpc.ItemReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetItemInfoMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ItemInfoService.
   */
  public static abstract class ItemInfoServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ItemInfoServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ItemInfoService.
   */
  public static final class ItemInfoServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ItemInfoServiceStub> {
    private ItemInfoServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ItemInfoServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ItemInfoServiceStub(channel, callOptions);
    }

    /**
     */
    public void getItemInfo(com.ecommerce.grpc.ItemRequest request,
        io.grpc.stub.StreamObserver<com.ecommerce.grpc.ItemReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetItemInfoMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ItemInfoService.
   */
  public static final class ItemInfoServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ItemInfoServiceBlockingStub> {
    private ItemInfoServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ItemInfoServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ItemInfoServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.ecommerce.grpc.ItemReply getItemInfo(com.ecommerce.grpc.ItemRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetItemInfoMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ItemInfoService.
   */
  public static final class ItemInfoServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ItemInfoServiceFutureStub> {
    private ItemInfoServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ItemInfoServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ItemInfoServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ecommerce.grpc.ItemReply> getItemInfo(
        com.ecommerce.grpc.ItemRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetItemInfoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ITEM_INFO = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ITEM_INFO:
          serviceImpl.getItemInfo((com.ecommerce.grpc.ItemRequest) request,
              (io.grpc.stub.StreamObserver<com.ecommerce.grpc.ItemReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetItemInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ecommerce.grpc.ItemRequest,
              com.ecommerce.grpc.ItemReply>(
                service, METHODID_GET_ITEM_INFO)))
        .build();
  }

  private static abstract class ItemInfoServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ItemInfoServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ecommerce.grpc.ItemProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ItemInfoService");
    }
  }

  private static final class ItemInfoServiceFileDescriptorSupplier
      extends ItemInfoServiceBaseDescriptorSupplier {
    ItemInfoServiceFileDescriptorSupplier() {}
  }

  private static final class ItemInfoServiceMethodDescriptorSupplier
      extends ItemInfoServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ItemInfoServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ItemInfoServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ItemInfoServiceFileDescriptorSupplier())
              .addMethod(getGetItemInfoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
