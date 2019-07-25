import Index from './components/Index'
import Search from './components/Search'
import SearchPageDetail from './components/SearchPageDetail'

const routes = [
    {path: '/', component: Index, name: '1'},
    {
        path: '/search',
        component: Search,
        name: '2',
        props: true
    },
    {
        path: '/search/detail',
        component: SearchPageDetail,
        name: '3',
        props: true
    },
];

export default routes