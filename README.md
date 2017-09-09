# 关于这个项目
因为 Java 的每一个类都可以添加 main 方法，并且所有的文件都是由若干个类组成的。
那么如果在本地构建了一个 算法/数据结构 库，在用 Java 写 ACM 的题目的时候，直接 import 就好了。
至于交到 OJ 的问题，只需要写一个脚本将 import 替换成相应的类就好了。


# 关于脚本 acm-build.py 的使用
建议将该文件链接到 `~/bin/acm-build`，对于使用 Windows 环境的同学，只要有 git 环境也是没差的（可以使用 `ln -s` 命令即可）。

```python3
src_packages = 'clown'
paths = [
    r'/d/workspace/inWindows/forJava/JetBrains/personal/acm/src/main/java',
    r'D:\workspace\inWindows\forJava\JetBrains\personal\acm\src\main\java'
]
```

根据自己的需要修改一下 `acm-build.py` 的这几个变量。
简要说明一下 `paths` 变量，因为在 Windows 下如果搭建 shell 环境的话，在命令行中执行脚本时路径和 Windows 的原生路径有区别；在 `paths` 下指定若干路径，会以第一个有效的路径为准。



比如你在 `oj/codeforces/Y2017/M09/D09/div2` 目录下写了一个 `A.java`，然后仅需在命令行中进入到这个目录，并执行 `acm-build A.java --save --pretty` 即可。
这里的 `--save` 和 `--pretty` 都是可选项：
> * 指定了 `--save` 则会生成一个 `Main.java` 文件，文件的位置自己试一下就知道了（`paths` 和 `src_packages` 作用的结果）
> * 指定了 `--pretty` 会保留空格和换行
另外，执行了 `acm-build` 之后会将生成的代码复制到剪切板，所以可以直接提交代码的 OJ 粘贴上去就好了。





