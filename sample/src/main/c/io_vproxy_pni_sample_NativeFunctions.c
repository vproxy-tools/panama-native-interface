#ifdef WIN32
  #include <Winsock2.h>
  #include <inttypes.h>
  typedef int32_t socklen_t;
#else
  #include <sys/socket.h>
  #include <arpa/inet.h>
  #define closesocket close
#endif

#include "io_vproxy_pni_sample_NativeFunctions.impl.h"
