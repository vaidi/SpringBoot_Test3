ServerSocketChannel serverChannel = ServerSocketChannel.open();// 打开一个未绑定的serversocketchannel   
Selector selector = Selector.open();// 创建一个Selector
serverChannel .configureBlocking(false);//设置非阻塞模式
serverChannel .register(selector, SelectionKey.OP_READ);//将ServerSocketChannel注册到Selector
while(true) {
  int readyChannels = selector.select();
  if(readyChannels == 0) continue;
  Set selectedKeys = selector.selectedKeys();
  Iterator keyIterator = selectedKeys.iterator();
  while(keyIterator.hasNext()) {
    SelectionKey key = keyIterator.next();
    if(key.isAcceptable()) {
        // a connection was accepted by a ServerSocketChannel.
    } else if (key.isConnectable()) {//连接就绪
        // a connection was established with a remote server.
    } else if (key.isReadable()) {//读就绪
        // a channel is ready for reading
    } else if (key.isWritable()) {//写就绪
        // a channel is ready for writing
    }
    keyIterator.remove();
  }
}

作者：新栋BOOK
链接：https://www.jianshu.com/p/db5da880154a
來源：简书
简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。