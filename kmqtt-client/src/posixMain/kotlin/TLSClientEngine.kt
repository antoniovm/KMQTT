import socket.tls.TLSClientSettings
import socket.tls.TLSEngine

internal expect class TLSClientEngine(tlsSettings: TLSClientSettings) : TLSEngine