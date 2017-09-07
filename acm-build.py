#! /usr/bin/env python3
#  -*- coding: UTF-8 -*-


__author__ = 'lemon clown'


import re
import os
import sys
import clipboard


paths = [
    r'/d/workspace/inWindows/forJava/JetBrains/personal/acm/src/main/java',
    r'D:\workspace\inWindows\forJava\JetBrains\personal\acm\src\main\java'
]


class RemoveUseless:
    re_public = re.compile(r'public\s+((?:class|interface|abstract\s+class|abstract\s+interface)\s+\w+)')
    re_packages = re.compile(r'package[\s\S]+?;\s*')
    re_comments1 = re.compile(r'[ \t]*//.*')
    re_comments2 = re.compile(r'\s*/[*][\s\S]*?[*]/[ \t]*')

    @staticmethod
    def toPublicMain(s, name):
        re_target = re.compile(r'class\s+{name}(\W)'.format(name=name))
        s = re_target.sub(lambda x: 'public class Main'+x.groups()[0], s)
        return s

    @classmethod
    def run(cls, s, *, name):
        s = cls.re_packages.sub('', s)
        s = cls.re_comments1.sub('', s)
        s = cls.re_comments2.sub('', s)
        s = cls.re_public.sub(lambda x: x.groups()[0], s)
        s = cls.toPublicMain(s, name)
        return s


class ReplaceImport:
    re_utils = re.compile(r'(lemon.clown[.]\S+?)')
    re_imports = re.compile(r'import\s+(\S+?);')

    def __init__(self):
        self.local_imports = set()
        self.java_imports = set()

    def run(self, s):
        def replace_import(x):
            target = x.groups()[0]
            if self.re_utils.match(target):
                if target in self.local_imports:
                    return ''
                self.local_imports.add(target)
                target = os.path.normpath(target.replace('.', '/'))
                target = os.path.splitext(target)[0] + '.java'
                with open(target, 'r', encoding='utf-8') as fin:
                    ans = fin.read()
                    ans = self.run(ans)
                    return ans
            else:
                self.java_imports.add(target)
        s = self.re_imports.sub(replace_import, s)
        return s

    def rereange_import(self):
        s = '\n'.join(map(lambda x: 'import '+x+';', sorted(self.java_imports)))
        return s


class RemoveSpaces:
    re_word = re.compile(r'\w')
    re_spaces = re.compile(r'\s+')

    @classmethod
    def run(cls, s):
        def replaceSpaces(s):
            length = len(s)
            def inner_replaceSpaces(x):
                lft, rht = x.span()
                lft -= 1
                if lft < 0 or rht >= length:
                    return ''
                if cls.re_word.match(s[lft]) and cls.re_word.match(s[rht]):
                    return ' '
                return ''
            return cls.re_spaces.sub(inner_replaceSpaces, s)

        ret = ""
        last = 0
        def inner_replace(x):
            nonlocal last
            nonlocal ret
            lft, rht = x.span()
            ret += replaceSpaces(s[last:lft])
            ret += x.group()
            last = rht

        re.compile('(?:"[\s\S]*?"|\'[\s\S]*?\')').sub(inner_replace, s)
        ret += replaceSpaces(s[last:])
        return ret

def adjustPublicClassPosition(content):
    split_point = 'public class Main'
    prefix, suffix = content.split(split_point)
    prefix = prefix.strip()
    suffix = split_point + suffix

    pos = re.search(r'class|interface', prefix)
    if pos:
        pos = pos.span()[0]
        ret = prefix[0:pos] + '\n\n' + suffix + '\n' + prefix[pos:]
    else:
        ret = content

    return ret


def solve(infile, outfile, target_name, *, zip_file, save_file):
    removeUseless = RemoveUseless()
    replaceImport = ReplaceImport()
    removeSpaces = RemoveSpaces()
    with open(infile, 'r', encoding='utf-8') as fin:
        content = fin.read()
        content = replaceImport.run(content)
        content = replaceImport.rereange_import() + '\n\n' + content
        content = removeUseless.run(content, name=target_name)

        content = adjustPublicClassPosition(content)

        if zip_file:
            content = removeSpaces.run(content)

        clipboard.copy(content)
        if save_file:
            with open(outfile, 'w', encoding='utf-8') as fout:
                fout.write(content)

def main():
    if len(sys.argv) >= 2:
        src = sys.argv[1]
    else:
        print('current path:', os.path.getcwd())
        src = input('src: ')

    src = os.path.normpath(src)
    src = os.path.splitext(src)[0] + '.java'
    src = os.path.abspath(src)

    zip_file = True
    save_file = False
    if len(sys.argv) >= 3:
        args = sys.argv
        for i in range(2, len(args)):
            if args[i] == '--pretty':
                zip_file = False
            elif args[i] == '--save':
                save_file = True

    prefix, suffix = os.path.split(src)
    target_name = os.path.splitext(suffix)[0]
    # dest = os.path.join(prefix, 'Main_'+suffix)

    src_packages = 'clown'
    dest = os.path.join(prefix.split(src_packages)[0], src_packages, 'Main.java')
    # dest = os.path.join(dest, 'Main.java')

    print('src:', src)
    print('dest:', dest)

    for path in paths:
        if os.path.exists(path):
            os.chdir(path)
            break
    solve(src, dest, target_name, zip_file=zip_file, save_file=save_file)

if __name__ == '__main__':
    main()

