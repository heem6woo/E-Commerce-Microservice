// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: id.proto

package com.ecommerce.grpc;

public final class MemberProto {
  private MemberProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_IdRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_IdRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_IdReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_IdReply_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\010id.proto\"7\n\tIdRequest\022\017\n\005email\030\001 \001(\tH\000" +
      "\022\016\n\004name\030\002 \001(\tH\000B\t\n\007request\"\025\n\007IdReply\022\n" +
      "\n\002id\030\001 \001(\0052,\n\tIdService\022\037\n\005getId\022\n.IdReq" +
      "uest\032\010.IdReply\"\000B#\n\022com.ecommerce.grpcB\013" +
      "MemberProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_IdRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_IdRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_IdRequest_descriptor,
        new java.lang.String[] { "Email", "Name", "Request", });
    internal_static_IdReply_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_IdReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_IdReply_descriptor,
        new java.lang.String[] { "Id", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
