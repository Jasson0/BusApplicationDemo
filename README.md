# BusApplicationDemo
【一】先看下工程结构：


该工程包含4个module：

【1】app module为一个空module，是整个工程的主module，其他module都是它的作为library。从可扩展性和代码健壮性考虑，这个module一般不写任何逻辑，不过需要注意的是，其manifest文件里要声明所有工程中的Activity。

【2】business module是一个公共module，所有的业务module都需要依赖这个module。这个module下一般存放一些工具类或封装好的一些组件，是整个工程的核心module。如果没有这个module，那每个module需要的工具类或者组件，都需要写一遍，不利于项目后续的扩展性。

【3】module1，module2，module3.......这些module就是业务module了，比如淘宝的“天猫超市”，“聚划算”，“品牌汇”等肯定就是一个一个的业务的module。



【二】Bus通信原理

Bus通信原理总结来说，可以归纳为两个词：多态+反射。多态是指每个module都继承一个抽象类BusObject，在需要通信时一律调BusObject的抽象方法，
那么其实调用的是具体实现类的相应方法。反射是指在没有依赖其他module，但想调到其他module的时，可以通过反射拿到类的实例，然后调用相应的方法。

这个类是一个抽象类，每个BU的都需要有一个BusObject类的子类。


这个类是bus的管理类，实际操作类是 实现”BusOBjectProvider“接口的实现类。


原理：
1.在baseApplication中初始化所有module，并且在BundleConfigFactory中注册module信息。
在BundleConfigFactory中维护了一张hashmap，来存储所有module的信息。
        bundleConfigModels.add(new BundleConfigModel(
                "leonmodule2",
                "com.example.leonmodule2",
                "com.example.leonmodule2.bus.ModuleTwoBusObject",
                BundleConfigModel.BundleLoadType.AutoLoad
        ));
包括module名，包名，busObject的包名，加载类型目前没用到过
2.当用户使用callData时，首先进入Bus.callData
   public static Object callData(Context context, String bizName, Object... param) {
        BusObject busObject = BusManager.findBusObject(BusManager.parseBizNameHost(bizName));
        try {
            if (busObject != null) {
                return busObject.doDataJob(context, bizName, param);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

   然后进入
   BusManager.findBusObject
   public static BusObject findBusObject(String hostName) {
           return busObjectProvider.findBusObject(hostName);
       }
   busObjectProvider是个接口，实现类是BusInit。

   进入BusInit
   在BusInit中维护了一张hashmap，用来存储所有BusObject。
       public BusObject findBusObject(String hostName) {
           if (TextUtils.isEmpty(hostName)) {
               return null;
           }
           BusObject object = hashMap.get(hostName.toLowerCase());
           if (object == null) {
               object = registerBusObjectWithHost(hostName);
           }
           return object;
       }

   核心代码在
   registerBusObjectWithHost
      public static BusObject registerBusObjectWithHost(String host) {
          if (TextUtils.isEmpty(host)) {
              return null;
          }
          BundleConfigModel bundleConfigModel = BundleConfigFactory.getBundleConfigModelByModuleName(host);
          if (null == bundleConfigModel) {
              return null;
          }
          BusObject retObj = null;
          try {
              String className = bundleConfigModel.busObjectName;
              Class<BusObject> clazz = (Class<BusObject>) Class.forName(className);
              Constructor<BusObject> constructor = clazz.getConstructor(String.class);
              BusObject temRetObj = constructor.newInstance(host);
              if (Bus.register(temRetObj)) {
                  retObj = temRetObj;
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          return retObj;
      }
   通过反射，来获得之前注册的busobject。然后只需要调用doDataJob即可
   在BundleConfigModel中，正真使用到的是moduleName和busObjectName，packageName没有使用到