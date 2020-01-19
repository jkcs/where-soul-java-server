#!/usr/bin/python
# encoding:utf8
"""  java项目启动脚本      """
import os
import platform
import sys
import time

"""     配置虚拟机参数   """
jvm_args = '-Dfile.encoding=UTF-8 -XX:MaxDirectMemorySize=128m -Xmx512m -Xms512m'

key = os.path.basename(sys.path[0] + '.jar')
log_path = os.path.basename(sys.path[0])
jar_path = os.path.join(sys.path[0], key)

"""     组合linux命令   """
linux_cmd = 'java ' + jvm_args + ' -jar ' + jar_path + ' 1>/dev/null 2>../' + log_path + '.txt &'
"""     组合windows命令   """
win_cmd = 'start javaw ' + jvm_args + ' -jar ' + jar_path
os.chdir(sys.path[0])

"""     判断是否是windows系统   """


def isWin():
    return platform.system() == 'Windows'


"""     判断是否是linux系统   """


def isLinux():
    return platform.system() == 'Linux'


"""     获取进程编号  """


def getPids():
    list = []
    if isWin():
        lines = os.popen('jps -l | findstr ' + key).readlines()
        for line in lines:
            list.append(line.split()[0])
    if isLinux():
        lines = os.popen('ps -ef | grep java ').readlines()
        for line in lines:
            if line.find(jar_path) >= 0:
                list.append(line.split()[1])
    return list


"""     判断程序是否运行  """


def isRun():
    return len(getPids()) > 0


"""     杀掉启动进程，目前简单实现kill，后期优化调用java方法关闭  """


def killByPid(id):
    if isWin():
        os.system('taskkill /f /pid ' + id)
    if isLinux():
        os.system('kill ' + id)


"""     启动项目  """


def start():
    if isRun():
        print 'start failed. program is already running,pid:' + str(getPids())
        return False
    if isWin():
        os.system(win_cmd)
    if isLinux():
        os.system(linux_cmd)
    isrun = False;
    for i in range(10):
        time.sleep(0.5)
        if isRun():
            isrun = True
            break
    if not isrun:
        print 'start failed!!!!!!!!!'
        return False
    for pid in getPids():
        print 'program start in pid:' + pid
        f = open("../" + log_path + "_id.txt", "a")
        f.write(time.strftime('%Y-%m-%d %H:%M:%S ') + pid + ' start\n')
        f.close()
    return True


"""     停止项目  """


def stop():
    list = getPids()
    if len(list) == 0:
        print 'stop failed. program is not start'
        return False
    for id in list:
        killByPid(id)
    isrun = True;
    for i in range(10):
        time.sleep(0.5)
        if not isRun():
            isrun = False
            break
    if isrun:
        print 'stop failed!!!!!!!!!'
        return False
    print 'program stopped.'
    f = open("../" + log_path + "_id.txt", "a")
    f.write(time.strftime('%Y-%m-%d %H:%M:%S ') + str(list) + ' stopped\n')
    f.close()
    return True


"""     限定输入命令，如果不合格，友好提示  """
if __name__ == '__main__':
    usage = 'usage: "server.py start|stop|restart"'
    argv = sys.argv
    if len(argv) >= 2:
        # print argv[0]+'...'
        if 'start' == argv[1]:
            start()
        elif 'stop' == argv[1]:
            stop()
        elif 'restart' == argv[1]:
            stop()
            start()
        else:
            print usage
    if len(argv) < 2:
        print usage
