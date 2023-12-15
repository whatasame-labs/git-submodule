# Git submodule

This project uses git submodule for managing dependencies. In this case, we will manage `application.yml` with
submodule.

## Getting started with Spring Boot

We will test if the submodule is imported correctly with development and production profile. Let's see how to use it.

### Clone repository

```bash
git clone --recurse-submodules https://github.com/whatasame-labs/git-submodule.git
```

### Run test

```bash
./gradlew test
```

## How to build new project importing submodule

You have to own the repository which you want to add as submodule already. See sample
repository [here](https://github.com/whatasame-labs/git-submodule-config).

> [!CAUTION]
>
> If your repository has sensitive information, you must make submodule private.

### Add submodule

Add submodule with following command.

```bash
git submodule add https://github.com/whatasame-labs/git-submodule-config.git config
```

You can see `.gitmodules` file is created. and you have to commit submodule in your repository.

```bash
git add .
git commit -m "build: Add submodule"
```

### Commit and push your change

If you want to commit after changing submodule in your repository, you have to commit two times.

1. Commit in submodule
    ```
   # in submodule directory
    git commit
    ```
2. Commit in main repository
    ```
   # in main repository
    git commit
    ```

And you have to push following instruction.

```bash
git push --recurse-submodules=check # or --recurse-submodules=on-demand
```

`--recurse-submodules=check` prevents from pushing when submodule is not pushed.

`--recurse-submodules=on-demand` pushes submodule automatically.

### Update submodule

If other developer updates submodule, you have to update it in your repository.

```bash
git submodule update --remote --merge
```

`--merge` prevents from DETACHED HEAD state.

## Spring Boot property detection

Spring Boot automatically detects `application.yml` in classpath and **config** directory. So we can use it without any
extra step if you use submodule directory as `./config`.

If you want to use another directory name, you have to define gradle task to copy it to classpath. For example,

```groovy
task copyFile(type: Copy) {
    from './my-config-dir'
    into 'src/main/resources'
    include '*.yml'
}
```

## Reference

[7.11 Git Tools - Submodules | Git - Submodules](https://git-scm.com/book/en/v2/Git-Tools-Submodules)

[7.2.3. External Application Properties | Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.external-config.files)

[Git submodule 사용하기 | 지금의 흔적](https://pinedance.github.io/blog/2019/05/28/Git-Submodule)
