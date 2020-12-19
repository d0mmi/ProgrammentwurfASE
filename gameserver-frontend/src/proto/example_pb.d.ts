import * as jspb from 'google-protobuf'



export class ExampleRequest extends jspb.Message {
  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): ExampleRequest.AsObject;
  static toObject(includeInstance: boolean, msg: ExampleRequest): ExampleRequest.AsObject;
  static serializeBinaryToWriter(message: ExampleRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): ExampleRequest;
  static deserializeBinaryFromReader(message: ExampleRequest, reader: jspb.BinaryReader): ExampleRequest;
}

export namespace ExampleRequest {
  export type AsObject = {
  }
}

export class ExampleResponse extends jspb.Message {
  getValue(): string;
  setValue(value: string): ExampleResponse;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): ExampleResponse.AsObject;
  static toObject(includeInstance: boolean, msg: ExampleResponse): ExampleResponse.AsObject;
  static serializeBinaryToWriter(message: ExampleResponse, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): ExampleResponse;
  static deserializeBinaryFromReader(message: ExampleResponse, reader: jspb.BinaryReader): ExampleResponse;
}

export namespace ExampleResponse {
  export type AsObject = {
    value: string,
  }
}

