# Criar um programa que leia o valor de um salário e um percentual de aumento. Em seguida mostrar na tela qual foi o valor do aumento e o valor final do salário com o aumento

# Variáveis
# salario_inicial, percentual_aumento, salario_final, aumento: number

# Início
def main():
    salario_inicial = int(input("Digite aqui seu salário atual: "))
    percentual_aumento = (
        int(input("Digite aqui o percentual de aumento: ")))/100
    salario_final = salario_inicial + percentual_aumento*salario_inicial
    aumento = salario_final - salario_inicial
    print("Seu salário inicial era R$", salario_inicial,
          "e seu salário aumentou R$", aumento, ", ficando em R$", salario_final)


main()
# Fim
