// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: id.proto

package com.ecommerce.grpc;

public interface IdRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:IdRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string email = 1;</code>
   * @return Whether the email field is set.
   */
  boolean hasEmail();
  /**
   * <code>string email = 1;</code>
   * @return The email.
   */
  java.lang.String getEmail();
  /**
   * <code>string email = 1;</code>
   * @return The bytes for email.
   */
  com.google.protobuf.ByteString
      getEmailBytes();

  /**
   * <code>string name = 2;</code>
   * @return Whether the name field is set.
   */
  boolean hasName();
  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  com.ecommerce.grpc.IdRequest.RequestCase getRequestCase();
}
