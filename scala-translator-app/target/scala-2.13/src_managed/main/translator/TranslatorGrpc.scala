package translator

object TranslatorGrpc {
  val METHOD_TRANSLATE: _root_.io.grpc.MethodDescriptor[translator.TranslateRequest, translator.TranslateReply] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("Translator", "Translate"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[translator.TranslateRequest])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[translator.TranslateReply])
      .setSchemaDescriptor(_root_.scalapb.grpc.ConcreteProtoMethodDescriptorSupplier.fromMethodDescriptor(translator.TranslatorProto.javaDescriptor.getServices.get(0).getMethods.get(0)))
      .build()
  
  val METHOD_DETECT_LANGUAGE: _root_.io.grpc.MethodDescriptor[translator.DetectLanguageRequest, translator.DetectLanguageReply] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("Translator", "DetectLanguage"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[translator.DetectLanguageRequest])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[translator.DetectLanguageReply])
      .setSchemaDescriptor(_root_.scalapb.grpc.ConcreteProtoMethodDescriptorSupplier.fromMethodDescriptor(translator.TranslatorProto.javaDescriptor.getServices.get(0).getMethods.get(1)))
      .build()
  
  val SERVICE: _root_.io.grpc.ServiceDescriptor =
    _root_.io.grpc.ServiceDescriptor.newBuilder("Translator")
      .setSchemaDescriptor(new _root_.scalapb.grpc.ConcreteProtoFileDescriptorSupplier(translator.TranslatorProto.javaDescriptor))
      .addMethod(METHOD_TRANSLATE)
      .addMethod(METHOD_DETECT_LANGUAGE)
      .build()
  
  trait Translator extends _root_.scalapb.grpc.AbstractService {
    override def serviceCompanion = Translator
    def translate(request: translator.TranslateRequest): scala.concurrent.Future[translator.TranslateReply]
    def detectLanguage(request: translator.DetectLanguageRequest): scala.concurrent.Future[translator.DetectLanguageReply]
  }
  
  object Translator extends _root_.scalapb.grpc.ServiceCompanion[Translator] {
    implicit def serviceCompanion: _root_.scalapb.grpc.ServiceCompanion[Translator] = this
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = translator.TranslatorProto.javaDescriptor.getServices.get(0)
    def scalaDescriptor: _root_.scalapb.descriptors.ServiceDescriptor = translator.TranslatorProto.scalaDescriptor.services(0)
    def bindService(serviceImpl: Translator, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition =
      _root_.io.grpc.ServerServiceDefinition.builder(SERVICE)
      .addMethod(
        METHOD_TRANSLATE,
        _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[translator.TranslateRequest, translator.TranslateReply] {
          override def invoke(request: translator.TranslateRequest, observer: _root_.io.grpc.stub.StreamObserver[translator.TranslateReply]): Unit =
            serviceImpl.translate(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
              executionContext)
        }))
      .addMethod(
        METHOD_DETECT_LANGUAGE,
        _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[translator.DetectLanguageRequest, translator.DetectLanguageReply] {
          override def invoke(request: translator.DetectLanguageRequest, observer: _root_.io.grpc.stub.StreamObserver[translator.DetectLanguageReply]): Unit =
            serviceImpl.detectLanguage(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
              executionContext)
        }))
      .build()
  }
  
  trait TranslatorBlockingClient {
    def serviceCompanion = Translator
    def translate(request: translator.TranslateRequest): translator.TranslateReply
    def detectLanguage(request: translator.DetectLanguageRequest): translator.DetectLanguageReply
  }
  
  class TranslatorBlockingStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[TranslatorBlockingStub](channel, options) with TranslatorBlockingClient {
    override def translate(request: translator.TranslateRequest): translator.TranslateReply = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_TRANSLATE, options, request)
    }
    
    override def detectLanguage(request: translator.DetectLanguageRequest): translator.DetectLanguageReply = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_DETECT_LANGUAGE, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): TranslatorBlockingStub = new TranslatorBlockingStub(channel, options)
  }
  
  class TranslatorStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[TranslatorStub](channel, options) with Translator {
    override def translate(request: translator.TranslateRequest): scala.concurrent.Future[translator.TranslateReply] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_TRANSLATE, options, request)
    }
    
    override def detectLanguage(request: translator.DetectLanguageRequest): scala.concurrent.Future[translator.DetectLanguageReply] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_DETECT_LANGUAGE, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): TranslatorStub = new TranslatorStub(channel, options)
  }
  
  def bindService(serviceImpl: Translator, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition = Translator.bindService(serviceImpl, executionContext)
  
  def blockingStub(channel: _root_.io.grpc.Channel): TranslatorBlockingStub = new TranslatorBlockingStub(channel)
  
  def stub(channel: _root_.io.grpc.Channel): TranslatorStub = new TranslatorStub(channel)
  
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = translator.TranslatorProto.javaDescriptor.getServices.get(0)
  
}