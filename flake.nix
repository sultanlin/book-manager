{
  description = "A Nix-flake-based Node.js and Java development environment";

  inputs.nixpkgs.url = "https://flakehub.com/f/NixOS/nixpkgs/0.1.*.tar.gz";

  outputs = {
    self,
    nixpkgs,
  }: let
    javaVersion = 21; # Change this value to update the whole stack

    supportedSystems = ["x86_64-linux" "aarch64-linux" "x86_64-darwin" "aarch64-darwin"];
    forEachSupportedSystem = f:
      nixpkgs.lib.genAttrs supportedSystems (system:
        f {
          pkgs = import nixpkgs {
            inherit system;
            overlays = [self.overlays.default];
          };
        });
  in {
    overlays.default = final: prev: let
      jdk = prev."jdk${toString javaVersion}";
    in rec {
      inherit jdk;
      maven = prev.maven.override {jdk_headless = jdk;};
      gradle = prev.gradle.override {java = jdk;};
      lombok = prev.lombok.override {inherit jdk;};

      nodejs = prev.nodejs;
      yarn = prev.yarn.override {inherit nodejs;};
    };

    devShells = forEachSupportedSystem ({pkgs}: {
      default = pkgs.mkShell {
        packages = with pkgs; [
          #Java
          gcc
          gradle
          jdk
          maven
          ncurses
          patchelf
          zlib

          # Node
          node2nix
          nodejs
          nodePackages.pnpm
          yarn
        ];

        shellHook = let
          loadLombok = "-javaagent:${pkgs.lombok}/share/java/lombok.jar";
          prev = "\${JAVA_TOOL_OPTIONS:+ $JAVA_TOOL_OPTIONS}";
        in ''
          export JAVA_TOOL_OPTIONS="${loadLombok}${prev}"
        '';
      };
    });
  };
}
