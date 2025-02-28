%Questão 1
casa(doran,martell).
casa(oberyn,martell).
casa(elia,martell).
casa(trystane,martell).
casa(sandSnakes,martell).

casa(luthor,tyrell).
casa(olenna,tyrell).
casa(mace,tyrell).
casa(margaery,tyrell).
casa(loras,tyrell).

casa(tywin,lannister).
casa(joanna,lannister).
casa(jaime,lannister).
casa(cersei,lannister).
casa(tyrion,lannister).

casa(ormund,baratheon).
casa(steffon,baratheon).
casa(robert,baratheon).
casa(stannis,baratheon).
casa(renly,baratheon).
casa(shireen,baratheon).
casa(joffrey,baratheon).
casa(myrcella,baratheon).
casa(tommen,baratheon).

casa(aegonI,targaryen).
casa(maekarI,targaryen).
casa(maesterAemon,targaryen).
casa(aegonV,targaryen).
casa(jaehaerysII,targaryen).
casa(rhaelle,targaryen).
casa(aerysII,targaryen).
casa(rhaegar,targaryen).
casa(viserys,targaryen).
casa(jonSnow,targaryen).
casa(daenerys,targaryen).

casa(rickard,stark).
casa(brandon,stark).
casa(eddard,stark).
casa(benjen,stark).
casa(lyanna,stark).
casa(robb,stark).
casa(sansa,stark).
casa(arya,stark).
casa(brandon,stark).
casa(rickon,stark).
casa(talisa,stark).

casa(hoster,tully).
casa(lysa,tully).
casa(edmure,tully).
casa(catelyn,tully).

casa(jon,arryn).
casa(robin,arryn).


local(doran,dorne).
local(oberyn,dorne).
local(elia,dorne).
local(trystane,dorne).
local(sandSnakes,dorne).

local(luthor,theReach).
local(olenna,theReach).
local(mace,theReach).
local(margaery,theReach).
local(loras,theReach).

local(tywin,westerland).
local(joanna,westerland).
local(jaime,westerland).
local(cersei,westerland).
local(tyrion,westerland).

local(ormund,stormlands).
local(steffon,stormlands).
local(robert,stormlands).
local(stannis,stormlands).
local(renly,stormlands).
local(shireen,stormlands).
local(joffrey,stormlands).
local(myrcella,stormlands).
local(tommen,stormlands).

local(aegonI,undefined).
local(maekarI,undefined).
local(maesterAemon,undefined).
local(aegonV,undefined).
local(jaehaerysII,undefined).
local(rhaelle,undefined).
local(aerysII,undefined).
local(rhaegar,undefined).
local(viserys,undefined).
local(jonSnow,undefined).
local(daenerys,undefined).

local(balonGreyjoy,theNorth).
local(theonGreyjoy,theNorth).
local(rickard,theNorth).
local(brandon,theNorth).
local(eddard,theNorth).
local(benjen,theNorth).
local(lyanna,theNorth).
local(robb,theNorth).
local(sansa,theNorth).
local(arya,theNorth).
local(brandon,theNorth).
local(rickon,theNorth).
local(talisa,theNorth).
local(rooseBolton,theNorth).
local(ramsayBolton,theNorth).

local(hoster,theRiverlands).
local(lysa,theRiverlands).
local(edmure,theRiverlands).
local(catelyn,theRiverlands).
local(littlefinger,theRiverlands).
local(walderFrey,theRiverlands).
local(roslinFrey,theRiverlands).

local(jon,theVale).
local(robin,theVale).


pai(doran,trystane).
pai(oberyn,sandSnakes).

pai(luthor,mace).
pai(mace,margaery).
pai(mace,loras).

pai(tywin,jaime).
pai(tywin,cersei).
pai(tywin,tyrion).

pai(jaime,joffrey).
pai(jaime,myrcella).
pai(jaime,tommen).

pai(ormund,steffon).
pai(steffon,robert).
pai(steffon,stannis).
pai(steffon,renly).
pai(robert,joffrey).
pai(robert,myrcella).
pai(robert,tommen).
pai(stannis,shireen).

pai(maekarI,maesterAemon).
pai(maekarI,aegonV).
pai(aegonV,rhaelle).
pai(aegonV,jaehaerysII).
pai(jaehaerysII,aerysII).
pai(aerysII,rhaegar).
pai(aerysII,viserys).
pai(aerysII,daenerys).
pai(rhaegar,jonSnow).

pai(rickard,brandon).
pai(rickard,eddard).
pai(rickard,benjen).
pai(rickard,lyanna).
pai(eddard,robb).
pai(eddard,sansa).
pai(eddard,arya).
pai(eddard,brandon).
pai(eddard,rickon).
pai(eddard,jonSnow).

pai(hoster,lysa).
pai(hoster,edmure).
pai(hoster,catelyn).

pai(jon,robin).


mae(elia,raenys).
mae(elia,aegon).

mae(olena,mace).

mae(joanna,jaime).
mae(joanna,cersei).
mae(joanna,tyrion).
mae(cersei,joffrey).
mae(cersei,myrcella).
mae(cersei,tommen).

mae(rhaelle,steffon).

mae(lyanna, jonSnow).

mae(catelyn,robb).
mae(catelyn,sansa).
mae(catelyn,arya).
mae(catelyn,brandon).
mae(catelyn,rickon).

mae(lysa,robin).


irmao(X,Y) :- pai(Z,X), pai(Z,Y), X\=Y.
irmao(X,Y) :- mae(Z,X), mae(Z,Y), X\=Y.


descendente(X,Y) :- pai(X,Y); mae(X,Y).
descendente(X,Y) :- descendente(X,Z), (pai(Z,Y); mae(Z,Y)).


casados(rhaegar,elia).
casados(renly,margaery).
casados(joffrey,margaery).
casados(tommen,margaery).
casados(robb,talisa).
casados(edmure,roslinFrey).
casados(littleFinger,lysa).

casados(X,Y) :- (((pai(X,Z), mae(Y,Z));(pai(Y,Z), mae(X,Z))), X\=Y).


casas_relacionadas(X,Y) :- casa(A,X), casa(B,Y), (casados(A,B); casados(B,A)).



%Questão 2
vazio([]).

pertence(_, []) :- vazio([1]).
pertence(X, [X|_]).
pertence(X, [_|T]) :- pertence(X, T).

somatorio([],0).
somatorio([H|T],Soma) :- somatorio(T,Soma_Tail), Soma is H + Soma_Tail.


indice([H|_],H,0).
indice(X,Y,I) :- X = [_|T], indice(T,Y,Z), I is Z + 1.


reverso(X,Y) :- reverse(X,Z), Y = Z.