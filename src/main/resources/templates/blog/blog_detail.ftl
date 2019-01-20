<!doctype html>
<html lang="en" dir="ltr">
<@common.header/>


<body>
<p>这学期开始我准备重新夯实一下Java基础，那么首当其冲的就是并发编程和Java虚拟机了，于是我在一个月的时间里终于把《Java并发编程实践总结》啃下来了，真的是啃，因为这本书对于没有多少并发编程经验的我来说实在晦涩难懂,以前做Android的时候网络编程还略微用过一点，现在学Java Web，Spring和Servlet都已经把多线程封装好了，没有什么机会用到，但是了解多线程的设计思想及其底层设计还是很重要的。</p>
<p><!-- more--></p>
<h2>竞态条件</h2>
<p></p>
<blockquote>
    <p>当某个计算的正确性取决于多个线程的交替执行时序时，就会发生竞态条件 。</p>
</blockquote>


<p>竞态条件是并发编程出现问题的一大主因，“先检查后执行”操作就是竞态条件的最常见的类型，就是根据一个可能失效的结果来决定怎么进行下一步计算。</p>
<pre><code>//竞态条件伪代码
if(一个可能为真可能为假的条件)
      操作A</code></pre>

<p>在多线程环境下，在你确定条件为真并在操作A执行前，另一个线程又将条件置为假，这将此代码置于一种不稳定的状态，即有可能是对的也有可能是错的，全靠运气。</p>
<h2>可重入</h2>
<p></p>
<blockquote>
    <p>如果某个线程试图获得一个已经由它自己持有的锁，如果成功，则这个锁是可重入的，否则是不可重入的</p>
</blockquote>


<p>重入锁的设计思想是每个锁关联一个计数值和一个线程。计数值为0，则没有任何线程持有该锁。若线程试图获取该锁而该锁计数值不为0时，则判断该线程是否是已经持有本锁的线程，如果是则获取成功，计数值+1。否则获取失败。线程每一次退出同步区时，计数值-1，当计数值为0时，该锁被释放。</p>
<p>内置锁不是可重入的，ReentrantLock则可以重入。</p>
<h2>重排序</h2>
<p></p>
<blockquote>
    <p>在编译器中生成的指令顺序，可以与源代码中的顺序不同</p>
</blockquote>


<p>第一次看到重排序时，我就在想JVM这不是在乱搞吗，我辛辛苦苦写的代码，你还来给我重排序。后来了解到重排序提升了编译效率，但是重排序机制也不是对所有代码都重排序，操作A和操作B之间如果满足了偏序关系，即“Happens-Before”关系，那么JVM就不会对它们的代码进行重排序。</p>
<h2>Volatile关键字</h2>
<p>把变量声明为Volatile后，编译器会注意到这个变量是共享的，因此不会把该变量上的操作与其他内存操作一起重排序，例如在多线程中需要设置一个变量为循环条件时，最好将其设为volatile</p>
<pre><code>volatile boolean asleep;
...
while(!asleep)
{
  .....
}</code></pre>

<p></p>
<h2>同步工具类</h2>
<p></p>
<h3>a.CountDownLatch</h3>
<p>CountDownLatch称之为闭锁，可以使一个或多个线程等待一组事件的发生。CountDownLatch主要有两个方法，countDown()和await()，没调用一次countdown则计数值减一，await将一直阻塞直到计数值为0。</p>
<h3>b.FutureTask</h3>
<p>FutureTask表示的计算是通过Callable来实现的，相当于一种可生成结果的Runnable，并且可以处于以下三种状态：等待运行，正在运行，运行完成。FutureTask.get方法调用后如果该任务已经完成，则立即返回结果，否则一直阻塞到任务完成或者抛出异常。</p>
<h2>无限制创建线程的不足</h2>
<p></p>
<ul>
    <li>线程生命周期的开销非常高。
    </li><li>资源消耗
    </li><li>稳定性因素，可能抛出OutOfMemoryError错误。
    </li></ul>


<h2>Executor</h2>
<p>由于无限制创建线程并不是一种好的策略，于是我们可以使用有界队列来防止这一情况的产生，Executor就应运而生了。</p>
<p>Executor基于生产者消费者模式，如果要在程序中实现一个生产者消费者模式，最简单的方法就是使用Executor。</p>
<h3>线程池</h3>
<p>线程池可以复用多个线程从而分摊线程创建和销毁过程中的巨大开销，由于任务到来时线程已经存在了从而还提高了响应性，可以调用Executors中的几个静态方法来创建一个线程池。</p>
<ul>
    <li><strong>newCachedThreadPool()</strong>
    </li></ul>


<p>线程池的规模无限制，线程超过需求就创建，小于需求就回收</p>
<ul>
    <li><strong>newFixedThreadPool(int)</strong>
    </li></ul>


<p>线程池的规模是固定的</p>
<ul>
    <li><strong>newScheduledThreadPool(int)</strong>
    </li></ul>


<p>固定长度的线程池，以延时或定时的方式执行任务</p>
<ul>
    <li><strong>SingleThreadExecutor()</strong>
    </li></ul>


<p>只包含一个线程的线程池</p>
<h3>饱和策略</h3>
<p>前面说了线程池使用有界队列来管理线程，如果饱和了怎么办呢？就需要用到提前定义的饱和策略:</p>
<ul>
    <li>AbortPolicy： 直接抛出异常,由调用者自己捕获编写处理代码。
    </li><li>DiscardPolicy： 不处理该任务，直接丢弃
    </li><li>DiscardOldestPolicy： 丢弃队列里最近的一个任务，并执行当前任务。
    </li><li>CallerRunsPolicy： 只用调用者所在线程来运行任务。
    </li></ul>


<h2>显式锁</h2>
<p></p>
<h3>Lock和ReentrantLock</h3>
<p>Lock和ReentrantLock与内置锁的区别是可以中断一个正在等待获取锁的线程，或者使该线程无限地等待下去，Lock和ReentrantLock必须在finally代码块中释放锁，否则一直持有锁就极易形成死锁</p>
<h3>读写锁</h3>
<p>读写锁其实是由两个Lock组成的</p>
<pre><code>public interface ReadWriteLock
{
  Lock readLock();
  Lock writeLock();
}</code></pre>

<p></p>
<p>在读写锁的加锁策略中，允许多个读操作同时进行，但每次只允许一个写操作。</p>
<p>以上就是我读第一遍的部分收获，此书太过经典，第一遍我大概只掌握了40%的知识，等以后有时间了再二刷。</p>
</body>
</html>