Appchainj 接口继承了 Appchain 和 AppchainjRx 两个接口，Appchainj 的实现类（比如JsonRpc2_0Appchainj），提供了方法以发送交易的方式对合约进行部署和函数调用。Appchainj 中没有提供将 solidity 合约转换为 java 类的方法，所以对合约的操作必须依赖合约或者合约函数的二进制码，即手动拼接参数。

[build](Nervosj?id=appChainj-build-nervosjservice-appChainj)  
[netPeer](Nervosj?id=requestlt-netpeercountgt-netpeer)  
[appMetaData](Nervosj?id=requestlt-appmetadatagt-appmetadatadefaultblockparameter-defaultblockparameter)  
[appBlockNumber](Nervosj?id=requestlt-appblocknumbergt-appblocknumber)  
[appGetBalance](Nervosj?id=requestlt-appgetbalancegt-appgetbalancestring-address-defaultblockparameter-defaultblockparameter)  
[appGetAbi](Nervosj?id=requestlt-appgetabigt-appgetabistring-contractaddress-defaultblockparameter-defaultblockparameter)  
[appGetTransactionCount](Nervosj?id=requestlt-appgettransactioncountgt-appgettransactioncountstring-address-defaultblockparameter-defaultblockparameter)  
[appGetCode](Nervosj?id=requestlt-appgetcodegt-appgetcodestring-address-defaultblockparameter-defaultblockparameter)  
[appSendRawTransaction](Nervosj?id=requestlt-appsendtransactiongt-appsendrawtransactionstring-signedtransactiondata) 
[appCall](Nervosj?id=requestlt-appcallgt-appcallcall-call-defaultblockparameter-defaultblockparameter)  
[appGetBlockByHash](Nervosj?id=requestlt-appblockgt-appgetblockbyhash-string-blockhash-boolean-returnfulltransactionobjects)  
[appGetBlockByNumber](Nervosj?id=requestlt-appblockgt-appgetblockbynumber-defaultblockparameter-defaultblockparameter-boolean-returnfulltransactionobjects)  
[appGetTransactionByHash](Nervosj?id=requestlt-apptransactiongt-appgettransactionbyhashstring-transactionhash)  
[appGetTransactionReceipt](Nervosj?id=requestlt-appgettransactionreceiptgt-appgettransactionreceiptstring-transactionhash)  
[appNewBlockFilter](Nervosj?id=requestlt-appfiltergt-appnewblockfilter)  
[appBlockHashFlowable](Nervosj?id=observableltstringgt-appblockhashobservable)  
[appNewFilter](Nervosj?id=requestlt-appfiltergt-appnewfilterorgnervosjprotocolcoremethodsrequestappfilter-appfilter)  
[appUninstallFilter](Nervosj?id=requestlt-appuninstallfiltergt-appuninstallfilterbiginteger-filterid)  
[appGetFilterChanges](Nervosj?id=requestlt-apploggt-appgetfilterchangesbiginteger-filterid)  
[appGetFilterLogs](Nervosj?id=requestlt-apploggt-appgetfilterlogsbiginteger-filterid)  
[appLogFlowable](Nervosj?id=observableltloggt-applogobservableappfilter-appfilter)  

#### `Appchainj build (AppchainjService appChainj)`
根据 AppchainjService 类型实例化 appchainj。

**参数**
appChainj - appchainjService 实例

**返回值**
Appchainj 实例

**示例**
```java
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
```
#### `Request<?, NetPeerCount> netPeer()`
获取当前连接节点数。

**参数**
无

**返回值**
Request<?, NetPeerCount>

**示例**
```java
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
NetPeerCount netPeerCount = service.netPeerCount().send();
BigInteger peerCount = netPeerCount.getQuantity();
```
#### `Request<?, AppMetaData> appMetaData(DefaultBlockParameter defaultBlockParameter)`
获取指定块高的元数据。

**参数**
defaultBlockParamter - 块高度的接口：数字或者关键字

**返回值**
Request<?, AppMetaData>

**示例**
```java
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
DefaultBlockParameter defaultParam = DefaultBlockParameter.valueOf("latest");
AppMetaDataResult result = service.appMetaData(defaultParam).send();
int chainId = result.chainId;
String chainName = result.chainName;
String genesisTS = result.genesisTimestamp;
```
#### `Request<?, AppBlockNumber> appBlockNumber()`
获取当前块高度。

**参数**
无

**返回值**
Request<?, AppBlockNumber>

**示例**
```java
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
AppBlockNumber result = service.appBlockNumber().send();
BigInteger blockNumber = result.getBlockNumber();
```
#### `Request<?, AppGetBalance> appGetBalance(String address, DefaultBlockParameter defaultBlockParameter))`
获取地址余额。

**参数**
address - 所要查询的地址
defaultBlockParameter - 块高度的接口：数字或者关键字

**返回值**
Request<?, AppGetBalance>

**示例**
```java
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
String addr = "{hex address}";
DefaultBlockParameter defaultBlockParameter = DefaultBlockParamter.valueOf("latest");
AppGetBalance getBalance = service.appGetBalance(addr, defaultBlockParamter).send();
BigInteger balance = getBalance.getBalance();
```

#### `Request<?, AppGetAbi> appGetAbi(String contractAddress, DefaultBlockParameter defaultBlockParameter)`
获取合约的Abi。

**参数**
address - 所要查询Abi的合约地址
defaultBlockParameter - 块高度的接口：数字或者关键字

**返回值**
Request<?, AppGetAbi>

**示例**
```java
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
String addr = "{hex address}";
DefaultBlockParameter defaultBlockParameter = DefaultBlockParamter.valueOf("latest");
AppGetAbi getAbi = service.appGetAbi(addr, defaultBlockParamter).send();
String abi = getAbi.getAbi();
```

#### `Request<?, AppGetTransactionCount> appGetTransactionCount(String address, DefaultBlockParameter defaultBlockParameter)`
获取账户发送合约数量。

**参数**
address - 所要查询的地址
defaultBlockParameter - 块高度的接口：数字或者关键字

**返回值**
Request<?, AppGetTransactionCount>

**示例**
```java
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
String addr = "{hex address}";
DefaultBlockParameter defaultBlockParameter = DefaultBlockParamter.valueOf("latest");
AppGetTransactionCount getTransactionCount = service.appGetTransactionCount(addr, defaultBlockParamter).send();
BigInteger txCount = getTransactionCount.getTransactionCount();
```

#### `Request<?, AppGetCode> appGetCode(String address, DefaultBlockParameter defaultBlockParameter)`
获取合约代码。

**参数**
address - 所要查询的地址
defaultBlockParameter - 块高度的接口：数字或者关键字

**返回值**
Request<?, AppGetCode>

**示例**
```java
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
String addr = "{hex address}";
DefaultBlockParameter defaultBlockParameter = DefaultBlockParamter.valueOf("latest");
AppGetCode getCode = service.appGetCode(addr, defaultBlockParamter).send();
Sring code = getCode.getCode();
```

#### `Request<?, AppSendTransaction> appSendRawTransaction(String signedTransactionData)`
向区块链节点发送序列化交易。

**参数**
signedTransaction - 经过签名的交易数据

**返回值**
Request<?, AppSendTransaction>

**示例**
```java
//create a signed transaction
Transaction tx = Transaction.createContractTransaction(BigInteger.valueOf(nonce), this.config.getQuota(), this.currentHeight + 88, 0, chainId, value, this.config.getCode());
tx.sign(this.config.getPrivateKey(), false, false);

//instantiate a Nervosj and send the transaction
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
AppSendTransaction sendTransaction = service.appSendRawTransaction(tx).send();

//get hash of the transaction
String hash = sendTransaction.getHash();
```

#### `Request<?, AppCall> appCall(Call call, DefaultBlockParameter defaultBlockParameter)`
调用合约接口。

**参数**
call - 合约方法的call的封装
defaultBlockParameter - 块高度的接口：数字或者关键字

**返回值**
Request<?, AppCall>

**示例**
```java
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
Call call = new Call(from, to, data);
AppCall appCall = service.appCall(call, DefaultBlockParameter.valueOf("latest")).send();
String result = call.getValue();
```

#### `Request<?, AppBlock> appGetBlockByHash (String blockHash, boolean returnFullTransactionObjects)`
根据块的哈希值查询块信息。

**参数**
blockHash - 块高度的接口：数字或者关键字
returnFullTransactionObjects - 是否返回交易信息 (True: 返回详细交易列表| False: 只返回交易hash)

**返回值**
Request<?, AppBlock>

**示例**
```java
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
String blockHash = "{block hash to search}";
AppBlock appBlock = service.appGetBlockByHash(blockHash, false).send();
```

#### `Request<?, AppBlock> appGetBlockByNumber (DefaultBlockParameter defaultBlockParameter, boolean returnFullTransactionObjects)`
根据块高度查询块信息。

**参数**
defaultBlockParameter - 块高度的接口：数字或者关键字
returnFullTransactionObjects - 是否返回交易信息 (True: 返回详细交易列表| False: 只返回交易hash)

**返回值**
Request<?, AppBlock>

**示例**
```java
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
AppBlock appBlock = service.appGetBlockByHash(DefaultBlockParameter.valueOf("latest"), false).send();
```
#### `Request<?, AppTransaction> appGetTransactionByHash(String transactionHash)`
根据哈希查询交易信息。

**参数**
transactionHash - 交易哈希

**返回值**
Request<?, AppTransaction>

**示例**
```java
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
String txHash = "{hash of transactino to be searched}";
AppTransaction responseTx = service.appGetTransactionByHash(txHash).send();
```
#### `Request<?, AppGetTransactionReceipt> appGetTransactionReceipt(String transactionHash)`
根据交易哈希查询交易回执。

**参数**
transactionHash - 交易哈希

**返回值**
Request<?, AppGetTransactionReceipt>

**示例**
```java
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
String txHash = "{hash of transactino to be searched}";
AppGetTransactionReceipt txReceipt = service.appGetTransactionReceipt(txHash).send();
```

#### `Request<?, AppFilter> appNewBlockFilter()`
创建一个新的块过滤器，当有新的块写入时通知。

**参数**
无

**返回值**
Request<?, AppFilter>

**示例**
```java
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
AppFilter appFilter = service.appNewBlockFilter().send();
BigInteger filterId = appFilter.getFilterId();
```

#### `Flowable<String> appBlockHashFlowable()`
新建一个Block Filter来监听新增块的哈希，返回一个Flowable，可以用交互的形式来监听块高的变化。

**参数**
无

**返回值**
Flowable<?, AppLog>

**示例**
```java
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
Flowable blockFitlerFlowable = service.appBlockHashFlowable();
AppLog logs = service.appGetFilterLogs(filterId).send();
        blockFitlerFlowable.subscribe(block -> {
            System.out.println(block.toString());
        });
```

#### `Request<?, AppFilter> appNewFilter(org.appChainj.protocol.core.methods.request.AppFilter appFilter)`
创建一个新的过滤器以用来监听合约中的 emit 的 event。

**参数**
appFilter - 针对于 Appchain 智能合约 event 的过滤器（定义在 Request 中的 appFilter）

**返回值**
Request<?, AppFilter>

**示例**
```java
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
org.appChainj.protocol.core.methods.request.AppFilter appFilter = new AppFilter(fromBlock, toBlock, addresses);
AppFilter appFilter = service.appNewFilter(txHash).send();
BigInteger filterId = appFilter.getFilterId();
```

#### `Request<?, AppUninstallFilter> appUninstallFilter(BigInteger filterId)`
移除已部署的过滤器。

**参数**
filterId - 过滤器Id

**返回值**
Request<?, AppUninstallFilter>

**示例**
```java
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
BigInteger filterId = {your filter Id };
AppUninstallFilter uninstallFilter = service.appUninstallFilter(filterId).send();
```

#### `Request<?, AppLog> appGetFilterChanges(BigInteger filterId)`
根据过滤器 Id 查询 log，返回上一次查询之后的所有 log。

**参数**
filterId - 过滤器Id

**返回值**
Request<?, AppLog>

**示例**
```java
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
BigInteger filterId = {your filter Id };
AppLog logs = service.appGetFilterChanges(filterId).send();
List<LogResult> results = logs.getLogs();
```

#### `Request<?, AppLog> appGetFilterLogs(BigInteger filterId)`
根据过滤器 Id 查询 log，返回符合输入 filter Id 的所有log。

**参数**
filterId - 过滤器Id

**返回值**
Request<?, AppLog>

**示例**
```java
Appchainj service = Appchainj.build(new HttpService("127.0.0.1"));
BigInteger filterId = {your filter Id };
AppLog logs = service.appGetFilterLogs(filterId).send();
List<LogResult> results = logs.getLogs();
```

#### `Flowable<Log> appLogFlowable(AppFilter appFilter)`
根据 AppFilter 来安装一个新的 Filter 用以获取历史 log 和监听新的 Log，返回一个 Flowable 以交互的模式监听 Log。

**参数**
AppFilter - 过滤器可以由`appNewFilter`来新建

**返回值**
Flowable<Log>

**示例**
```java
Flowable appLogFlowable = service.appLogFlowable(filter);
            Flowable<String> reponse = appLogFlowable.map(
                    (log) -> {
                        EventValues eventValues = staticExtractEventParameters(event, (Log)log);
                        return (String) eventValues.getIndexedValues().get(0).getValue();;
                    }
                    );

            reponse.subscribe(x -> {
                System.out.println(x);
            });
```