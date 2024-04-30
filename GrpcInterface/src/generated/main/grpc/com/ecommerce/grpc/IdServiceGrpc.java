package com.ecommerce.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: id.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class IdServiceGrpc {

  private IdServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "IdService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ecommerce.grpc.IdRequest,
      com.ecommerce.grpc.IdReply> getGetIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getId",
      requestType = com.ecommerce.grpc.IdRequest.class,
      responseType = com.ecommerce.grpc.IdReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ecommerce.grpc.IdRequest,
      com.ecommerce.grpc.IdReply> getGetIdMethod() {
    io.grpc.MethodDescriptor<com.ecommerce.grpc.IdRequest, com.ecommerce.grpc.IdReply> getGetIdMethod;
    if ((getGetIdMethod = IdServiceGrpc.getGetIdMethod) == null) {
      synchronized (IdServiceGrpc.class) {
        if ((getGetIdMethod = IdServiceGrpc.getGetIdMethod) == null) {
          IdServiceGrpc.getGetIdMethod = getGetIdMethod =
              io.grpc.MethodDescriptor.<com.ecommerce.grpc.IdRequest, com.ecommerce.grpc.IdReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ecommerce.grpc.IdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ecommerce.grpc.IdReply.getDefaultInstance()))
              .setSchemaDescriptor(new IdServiceMethodDescriptorSupplier("getId"))
              .build();
        }
      }
    }
    return getGetIdMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static IdServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IdServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IdServiceStub>() {
        @java.lang.Override
        public IdServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IdServiceStub(channel, callOptions);
        }
      };
    return IdServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static IdServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IdServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IdServiceBlockingStub>() {
        @java.lang.Override
        public IdServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IdServiceBlockingStub(channel, callOptions);
        }
      };
    return IdServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static IdServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IdServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IdServiceFutureStub>() {
        @java.lang.Override
        public IdServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IdServiceFutureStub(channel, callOptions);
        }
      };
    return IdServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getId(com.ecommerce.grpc.IdRequest request,
        io.grpc.stub.StreamObserver<com.ecommerce.grpc.IdReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetIdMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service IdService.
   */
  public static abstract class IdServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return IdServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service IdService.
   */
  public static final class IdServiceStub
      extends io.grpc.stub.AbstractAsyncStub<IdServiceStub> {
    private IdServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IdServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IdServiceStub(channel, callOptions);
    }

    /**
     */
    public void getId(com.ecommerce.grpc.IdRequest request,
        io.grpc.stub.StreamObserver<com.ecommerce.grpc.IdReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetIdMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service IdService.
   */
  public static final class IdServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<IdServiceBlockingStub> {
    private IdServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IdServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IdServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.ecommerce.grpc.IdReply getId(com.ecommerce.grpc.IdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetIdMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service IdService.
   */
  public static final class IdServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<IdServiceFutureStub> {
    private IdServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IdServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IdServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ecommerce.grpc.IdReply> getId(
        com.ecommerce.grpc.IdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ID = 0;

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
        case METHODID_GET_ID:
          serviceImpl.getId((com.ecommerce.grpc.IdRequest) request,
              (io.grpc.stub.StreamObserver<com.ecommerce.grpc.IdReply>) responseObserver);
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
          getGetIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ecommerce.grpc.IdRequest,
              com.ecommerce.grpc.IdReply>(
                service, METHODID_GET_ID)))
        .build();
  }

  private static abstract class IdServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    IdServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ecommerce.grpc.MemberProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("IdService");
    }
  }

  private static final class IdServiceFileDescriptorSupplier
      extends IdServiceBaseDescriptorSupplier {
    IdServiceFileDescriptorSupplier() {}
  }

  private static final class IdServiceMethodDescriptorSupplier
      extends IdServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    IdServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (IdServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new IdServiceFileDescriptorSupplier())
              .addMethod(getGetIdMethod())
              .build();
        }
      }
    }
    return result;
  }
}
