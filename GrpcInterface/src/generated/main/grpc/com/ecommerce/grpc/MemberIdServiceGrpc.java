package com.ecommerce.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: member.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MemberIdServiceGrpc {

  private MemberIdServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "MemberIdService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ecommerce.grpc.IdRequest,
      com.ecommerce.grpc.IdReply> getGetMemberIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getMemberId",
      requestType = com.ecommerce.grpc.IdRequest.class,
      responseType = com.ecommerce.grpc.IdReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ecommerce.grpc.IdRequest,
      com.ecommerce.grpc.IdReply> getGetMemberIdMethod() {
    io.grpc.MethodDescriptor<com.ecommerce.grpc.IdRequest, com.ecommerce.grpc.IdReply> getGetMemberIdMethod;
    if ((getGetMemberIdMethod = MemberIdServiceGrpc.getGetMemberIdMethod) == null) {
      synchronized (MemberIdServiceGrpc.class) {
        if ((getGetMemberIdMethod = MemberIdServiceGrpc.getGetMemberIdMethod) == null) {
          MemberIdServiceGrpc.getGetMemberIdMethod = getGetMemberIdMethod =
              io.grpc.MethodDescriptor.<com.ecommerce.grpc.IdRequest, com.ecommerce.grpc.IdReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getMemberId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ecommerce.grpc.IdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ecommerce.grpc.IdReply.getDefaultInstance()))
              .setSchemaDescriptor(new MemberIdServiceMethodDescriptorSupplier("getMemberId"))
              .build();
        }
      }
    }
    return getGetMemberIdMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MemberIdServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MemberIdServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MemberIdServiceStub>() {
        @java.lang.Override
        public MemberIdServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MemberIdServiceStub(channel, callOptions);
        }
      };
    return MemberIdServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MemberIdServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MemberIdServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MemberIdServiceBlockingStub>() {
        @java.lang.Override
        public MemberIdServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MemberIdServiceBlockingStub(channel, callOptions);
        }
      };
    return MemberIdServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MemberIdServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MemberIdServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MemberIdServiceFutureStub>() {
        @java.lang.Override
        public MemberIdServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MemberIdServiceFutureStub(channel, callOptions);
        }
      };
    return MemberIdServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getMemberId(com.ecommerce.grpc.IdRequest request,
        io.grpc.stub.StreamObserver<com.ecommerce.grpc.IdReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMemberIdMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service MemberIdService.
   */
  public static abstract class MemberIdServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MemberIdServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service MemberIdService.
   */
  public static final class MemberIdServiceStub
      extends io.grpc.stub.AbstractAsyncStub<MemberIdServiceStub> {
    private MemberIdServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MemberIdServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MemberIdServiceStub(channel, callOptions);
    }

    /**
     */
    public void getMemberId(com.ecommerce.grpc.IdRequest request,
        io.grpc.stub.StreamObserver<com.ecommerce.grpc.IdReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMemberIdMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service MemberIdService.
   */
  public static final class MemberIdServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MemberIdServiceBlockingStub> {
    private MemberIdServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MemberIdServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MemberIdServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.ecommerce.grpc.IdReply getMemberId(com.ecommerce.grpc.IdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMemberIdMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service MemberIdService.
   */
  public static final class MemberIdServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<MemberIdServiceFutureStub> {
    private MemberIdServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MemberIdServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MemberIdServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ecommerce.grpc.IdReply> getMemberId(
        com.ecommerce.grpc.IdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMemberIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_MEMBER_ID = 0;

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
        case METHODID_GET_MEMBER_ID:
          serviceImpl.getMemberId((com.ecommerce.grpc.IdRequest) request,
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
          getGetMemberIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ecommerce.grpc.IdRequest,
              com.ecommerce.grpc.IdReply>(
                service, METHODID_GET_MEMBER_ID)))
        .build();
  }

  private static abstract class MemberIdServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MemberIdServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ecommerce.grpc.MemberProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MemberIdService");
    }
  }

  private static final class MemberIdServiceFileDescriptorSupplier
      extends MemberIdServiceBaseDescriptorSupplier {
    MemberIdServiceFileDescriptorSupplier() {}
  }

  private static final class MemberIdServiceMethodDescriptorSupplier
      extends MemberIdServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    MemberIdServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (MemberIdServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MemberIdServiceFileDescriptorSupplier())
              .addMethod(getGetMemberIdMethod())
              .build();
        }
      }
    }
    return result;
  }
}
