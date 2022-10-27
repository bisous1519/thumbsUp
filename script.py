import os

folder_name = 'week'
folder_list = [i for i in os.listdir() if folder_name in i]
locate = ''

def make_files(locate:str)->None:
    os.chdir(locate)
    with open('README.md', 'w') as f:
        f.write(f'# {locate}')
    for i in ['bisous1519', 'cksgnlcjswo', 'Hoony-JSG', 'mickeyshoes', 'ymj3539', 'dolpongg', 'Choseungheee']:
        os.mkdir(i)
        os.chdir(i)
        with open('README.md', 'w') as f:
            f.write(f'# {locate}')
        os.chdir('../')

if not folder_list:
    locate = folder_name+'0'
else:
    folder_list = sorted([int(i.replace(folder_name,'')) for i in folder_list])
    locate = folder_name+f'{folder_list[-1]+1}'

os.mkdir(locate)
make_files(locate)
