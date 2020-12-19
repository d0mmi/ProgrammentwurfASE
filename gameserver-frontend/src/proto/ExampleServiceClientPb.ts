/**
 * @fileoverview gRPC-Web generated client stub for example
 * @enhanceable
 * @public
 */

// GENERATED CODE -- DO NOT EDIT!


/* eslint-disable */
// @ts-nocheck


import * as grpcWeb from 'grpc-web';

import * as example_pb from './example_pb';


export class ExampleClient {
  client_: grpcWeb.AbstractClientBase;
  hostname_: string;
  credentials_: null | { [index: string]: string; };
  options_: null | { [index: string]: any; };

  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: any; }) {
    if (!options) options = {};
    if (!credentials) credentials = {};
    options['format'] = 'text';

    this.client_ = new grpcWeb.GrpcWebClientBase(options);
    this.hostname_ = hostname;
    this.credentials_ = credentials;
    this.options_ = options;
  }

  methodInfogetValue = new grpcWeb.AbstractClientBase.MethodInfo(
    example_pb.ExampleResponse,
    (request: example_pb.ExampleRequest) => {
      return request.serializeBinary();
    },
    example_pb.ExampleResponse.deserializeBinary
  );

  getValue(
    request: example_pb.ExampleRequest,
    metadata: grpcWeb.Metadata | null): Promise<example_pb.ExampleResponse>;

  getValue(
    request: example_pb.ExampleRequest,
    metadata: grpcWeb.Metadata | null,
    callback: (err: grpcWeb.Error,
               response: example_pb.ExampleResponse) => void): grpcWeb.ClientReadableStream<example_pb.ExampleResponse>;

  getValue(
    request: example_pb.ExampleRequest,
    metadata: grpcWeb.Metadata | null,
    callback?: (err: grpcWeb.Error,
               response: example_pb.ExampleResponse) => void) {
    if (callback !== undefined) {
      return this.client_.rpcCall(
        this.hostname_ +
          '/example.Example/getValue',
        request,
        metadata || {},
        this.methodInfogetValue,
        callback);
    }
    return this.client_.unaryCall(
    this.hostname_ +
      '/example.Example/getValue',
    request,
    metadata || {},
    this.methodInfogetValue);
  }

}

