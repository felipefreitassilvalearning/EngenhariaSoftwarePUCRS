db.viagens.find({
    "Partida.Bairro": "Floresta"
}, {
    "Carro_Codigo": {"Codigo": 1},
    "_id": 0
})

db.viagens.aggregate([
    {
        $unwind: "$Passageiros_CPF"
    },
    {
        $expr: {$eq:[
            "$Partida.Bairro", "$Passageiros_CPF.Bairro"
        ]}
    },
    {
        $project: {
            "Passageiros_CPF.Nome": 1,
            "Carro_Codigo.Codigo": 1
        }
    }
])

db.viagens.aggregate([
    {
        $unwind: "$Passageiros_CPF"
    },
    {
        $group: {
            _id: "$Passageiros_CPF.CPF",
            "Nome Passageiro": {$first: "$Passageiros_CPF.Nome"},
            "Quilômetros Percorridos": {$sum: "$Kms"}
        }
    },
    {
        $sort: {
            "Quilômetros Percorridos": -1
        }
    }
])
